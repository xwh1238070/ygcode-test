/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.jt.teng.fw.core.dataaccess;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.ygsoft.jt.teng.fw.core.dataaccess.extend.inter.IModelTableStrategy;
import com.ygsoft.jt.teng.fw.core.dataaccess.extend.inter.ISqlFilterStrategy;
import com.ygsoft.jt.teng.fw.core.service.model.ItemCondition;

/**
 * 九天-数据持久化服务.<br>
 * 
 * @author pengxiao <br>
 * @version 1.0.0 2021年2月24日<br>
 * @since JDK 1.8.0
 */
public interface IJtDataPersistService {
	/**
	 * 设置SQL过滤策略函数.
	 * @param strategy SQL过滤条件策略函数
	 * @return this
	 */
	IJtDataPersistService with(ISqlFilterStrategy strategy);
	/**
	 * 设置物理表名策略函数.
	 * @param strategy 物理表名策略函数
	 * @return this
	 */
	IJtDataPersistService with(IModelTableStrategy strategy);
	/**
	 * 保存实体.
	 * 
	 * @param entity
	 *            entity
	 */
	void save(Object entity);

	/**
	 * 保存实体.
	 * 
	 * @param entityList
	 *            entityList
	 */
	void save(List<?> entityList);

	/**
	 * 新增实体.
	 * 
	 * @param entity
	 *            entity
	 */
	void add(Object entity);

	/**
	 * 新增实体.
	 * 
	 * @param entity
	 *            entity List
	 */
	void add(List<?> entity);

	/**
	 * 更新实体.
	 * 
	 * @param entity
	 *            entity
	 */
	void update(Object entity);

	/**
	 * 更新实体.
	 * 
	 * @param entity
	 *            entity List
	 */
	void update(List<?> entity);

	/**
	 * 根据主键查找.
	 * 
	 * @param primaryKey
	 *            主键
	 * @return 实体
	 */
	Object find(Serializable primaryKey);

	/**
	 * 根据主键查找.
	 * 
	 * @param idList
	 *            主键列表
	 * @return 实体列表
	 */
	List<?> find(List<? extends Serializable> idList);

	/**
	 * 删除实体.
	 * 
	 * @param primaryKey
	 *            实体主键
	 */
	void delete(Serializable primaryKey);
	
	/**
	 * 删除实体(带子表，maxSubLevel决定子表层级).
	 * 
	 * @param primaryKey
	 *            实体主键
	 */
	void deleteWithSub(Serializable primaryKey);

	/**
	 * 删除实体.
	 * 
	 * @param ids
	 *            实体主键列表
	 */
	void delete(List<? extends Serializable> ids);
	
	/**
	 * 删除实体(带子表，maxSubLevel决定子表层级).
	 * 
	 * @param ids
	 *            实体主键列表
	 */
	void deleteWithSub(List<? extends Serializable> ids);

	/**
	 * 按条件删除.
	 * 
	 * @param conditions
	 *            条件
	 */
	void deleteByCondition(List<ItemCondition> conditions);

	/**
	 * 按条件删除带子表.
	 * 
	 * @param conditions
	 *            条件
	 */
	void deleteWithSubByCondition(List<ItemCondition> conditions);

	/**
	 * 按条件更新.
	 * 
	 * @param fieldValueMap
	 *            更新字段-值Map
	 * @param conditions
	 *            条件
	 */
	void updateByCondition(Map<String, Object> fieldValueMap, List<ItemCondition> conditions);
}
