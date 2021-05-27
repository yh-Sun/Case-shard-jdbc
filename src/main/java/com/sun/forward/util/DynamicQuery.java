package com.sun.forward.util;

import org.hibernate.SQLQuery;
import org.hibernate.query.NativeQuery;
import org.hibernate.transform.Transformers;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class DynamicQuery {

    @PersistenceContext
    private EntityManager em;

    public EntityManager getEntityManager() {
        return em;
    }

    /**
     * 保存
     *
     * @param entity 数据对象
     */
    public void save(Object entity) {
        em.persist(entity);
    }

    /**
     * 更新
     *
     * @param entity 数据对象
     */
    public void update(Object entity) {
        em.merge(entity);
    }

    /**
     * 删除
     *
     * @param entityClass 数据类型
     * @param entityid    数据主键
     * @param <T>         数据类型
     */
    public <T> void delete(Class<T> entityClass, Object entityid) {
        delete(entityClass, new Object[]{entityid});
    }

    /**
     * 删除
     *
     * @param entityClass 数据类型
     * @param entityids   数据集合
     * @param <T>         数据类型
     */
    public <T> void delete(Class<T> entityClass, Object[] entityids) {
        for (Object id : entityids) {
            em.remove(em.getReference(entityClass, id));
        }
    }

    /**
     * 获取数据查询query对象
     *
     * @param sql    sql
     * @param params 参数
     * @return query对象
     */
    public Query createNativeQuery(String sql, Object... params) {
        Query q = em.createNativeQuery(sql);
        if (params != null && params.length > 0) {
            for (int i = 0; i < params.length; i++) {
                // 与Hiberante不同,jpa query从位置1开始
                q.setParameter(i + 1, params[i]);
            }
        }
        return q;
    }

    /**
     * 动态查询列表
     *
     * @param nativeSql 原生sql
     * @param params    参数
     * @param <T>       类型
     * @return 数据结果集
     */
    public <T> List<T> nativeQueryList(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.TO_LIST);
        return q.getResultList();
    }

    /**
     * 动态查询指定类型数据列表
     *
     * @param resultClass 结果类型
     * @param nativeSql   原生sql
     * @param params      参数
     * @param <T>         数据类型
     * @return 数据结果集
     */
    public <T> List<T> nativeQueryListModel(Class<T> resultClass,
                                            String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(NativeQuery.class).setResultTransformer(Transformers.aliasToBean(resultClass));
        return q.getResultList();
    }

    /**
     * 动态查询map列表
     *
     * @param nativeSql 原生sql
     * @param params    参数
     * @param <T>       类型
     * @return 列表map
     */
    public <T> List<T> nativeQueryListMap(String nativeSql, Object... params) {
        Query q = createNativeQuery(nativeSql, params);
        q.unwrap(SQLQuery.class).setResultTransformer(Transformers.ALIAS_TO_ENTITY_MAP);
        return q.getResultList();
    }

    /**
     * 动态查询数量
     *
     * @param nativeSql 原生sql
     * @param params    参数
     * @return 总数
     */
    public Long nativeQueryCount(String nativeSql, Object... params) {
        Object count = createNativeQuery(nativeSql, params).getSingleResult();
        return ((Number) count).longValue();
    }

}
