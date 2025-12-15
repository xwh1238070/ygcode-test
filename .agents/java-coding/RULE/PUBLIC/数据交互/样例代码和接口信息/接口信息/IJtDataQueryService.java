/*
 * Copyright (C) 2005 - 2030 YGSoft.Inc All Rights Reserved.
 * YGSoft.Inc PROPRIETARY/CONFIDENTIAL.Use is subject to license terms.
 */
package com.ygsoft.jt.teng.fw.core.dataaccess;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.ygsoft.jt.teng.fw.core.dataaccess.extend.inter.IModelTableStrategy;
import com.ygsoft.jt.teng.fw.core.dataaccess.extend.inter.ISqlFilterStrategy;
import com.ygsoft.jt.teng.fw.core.service.model.ItemCondition;
import com.ygsoft.jt.teng.fw.core.service.model.PageModel;

/**
 * 九天-数据持查询服务.<br>
 * 
 * @author pengxiao <br>
 * @version 1.0.0 2021年2月27日<br>
 * @since JDK 1.8.0
 */
public interface IJtDataQueryService {
	/**
	 * 设置SQL过滤策略函数.
	 * @param strategy SQL过滤条件策略函数
	 * @return this
	 */
	IJtDataQueryService with(ISqlFilterStrategy strategy);
	/**
	 * 设置物理表名策略函数.
	 * @param strategy 物理表名策略函数
	 * @return this
	 */
	IJtDataQueryService with(IModelTableStrategy strategy);
	/**
	 * 根据ID查找数据.
	 * 
	 * @param id
	 *            ID
	 * @return 数据对象
	 */
	Object find(Serializable id);
	/**
	 * 根据ID查找数据.
	 * 
	 * @param id
	 *            ID
	 * @return 数据对象
	 */
	Optional<?> findById(Serializable id);

	/**
	 * 根据ID列表查询.
	 * 
	 * @param ids
	 *            ID List
	 * @return 数据对象列表
	 */
	List<?> find(List<? extends Serializable> ids);

	/**
	 * 分页查找数据.
	 * 
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findAll(int pageNo, int pageSize);

	/**
	 * 分页排序查找.
	 * 
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 分页查找结果
	 */
	PageModel<?> findAll(int pageNo, int pageSize, String sort);


	/**
	 * 按例查询,头1000行记录.
	 * @param example 样例
	 * @return 按样例分页查找结果
	 */
	PageModel<?> findPageByExample(final Object example);

	/**
	 * 按例查询,头1000行记录.
	 * @param example 样例
	 * @return 按样例分页查找结果
	 */
	PageModel<?> findPageByExample(final Object example, final String sort);

								   /**
                                    * 按例查询.
                                    * @param example 样例
                                    * @param pageNo 分页号
                                    * @param pageSize 分页大小
                                    * @return 按样例分页查找结果
                                    */
	PageModel<?> findPageByExample(final Object example , final int pageNo , final int pageSize);


	/**
	 * 按例查询.
	 * @param example 样例
	 * @param pageNo 分页号
	 * @param pageSize 分页大小
	 * @param sort 排序
	 * @return 按样例分页查找结果
	 */
	PageModel<?> findPageByExample(final Object example , final int pageNo , final int pageSize, final String sort);

	/**
	 * 条件分页查询.
	 * 
	 * @param itemCodition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findByPageWithCondition(List<ItemCondition> itemCodition, int pageNo, int pageSize);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findByPageWithCondition(String itemCondition, int pageNo, int pageSize);

	/**
	 * 条件分页排序查询.
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象
	 * @return 分页查找结果
	 */
	PageModel<?> findByPageWithCondition(List<ItemCondition> itemCondition, int pageNo, int pageSize,
			String sort);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 分页查找结果
	 */
	PageModel<?> findByPageWithCondition(String itemCondition, int pageNo, int pageSize,
			String sort);

	/**
	 * 条件查询数据量.
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @return 数据量
	 */
	long count(List<ItemCondition> itemCondition);

	/**
	 * 根据对象SQL查询.
	 * 
	 * @param sql
	 *            对象SQL语句(select userId, userName from User where userName='abc' and
	 *            userId=:userId)
	 * @param params
	 *            sql参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 查询结果
	 */
	PageModel<?> findPageBySQL(String sql, Map<String, Object> params, int pageNo,
			int pageSize);

	/**
	 * 根据对象SQL查询.
	 * 
	 * @param sql
	 *            对象SQL语句
	 * @param params
	 *            sql参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findBySQL(String sql, Map<String, Object> params, int pageNo, int pageSize);


	/**
	 * 根据对象SQL查询, 头1000行.
	 *
	 * @param selectFields 查询的属性
	 * @param filterMap 字段：条件/条件数组
	 * @return 数据列表
	 */
	List<?> findByFilterMap(final List<String> selectFields, final Map<String, Object> filterMap);


	/**
	 * 根据对象SQL查询, 头1000行.
	 *
	 * @param selectFields 查询的属性
	 * @param filterMap 字段：条件/条件数组
	 * @param sort 排序
	 * @return 数据列表
	 */
	List<?> findByFilterMap(final List<String> selectFields, final Map<String, Object> filterMap, final String sort);

	/**
	 * 根据对象SQL查询.
	 *
	 * @param selectFields 查询的属性
	 * @param filterMap 字段：条件/条件数组
	 * @param pageNo  页码
	 * @param pageSize 分页大小
	 * @param sort 排序
	 * @return 数据列表
	 */
	List<?> findByFilterMap(final List<String> selectFields, final Map<String, Object> filterMap,
							final int pageNo, final int pageSize, final String sort);

	/**
	 * 根据对象过滤条件查询.
	 * 
	 * @param filter
	 *            对象过滤条件
	 * @param params
	 *            sql参数
	 * @return 数据列表
	 */
	List<?> findByFilter(String filter, Map<String, Object> params);
	
	/**
	 * 根据对象过滤条件查询.
	 * 
	 * @param filter
	 *            对象过滤条件
	 * @param params
	 *            sql参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findByFilter(String filter, Map<String, Object> params, int pageNo, int pageSize);
	
	/**
	 * 根据对象过滤条件查询.
	 * 
	 * @param filter
	 *            对象过滤条件
	 * @param params
	 *            sql参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findByFilter(String filter, Map<String, Object> params, int pageNo, int pageSize, String sort);	
	
	/**
	 * 根据对象过滤条件查询.
	 * 
	 * @param filter
	 *            对象过滤条件
	 * @param params
	 *            sql参数
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 分页模型
	 */
	PageModel<?> findPageByFilter(String filter, Map<String, Object> params, int pageNo, int pageSize, String sort);

	/**
	 * 条件查询.
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findListByPageWithCondition(List<ItemCondition> itemCondition, int pageNo, int pageSize);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findListByPageWithCondition(String itemCondition, int pageNo, int pageSize);

	/**
	 * 条件排序查询.
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findListByPageWithCondition(List<ItemCondition> itemCondition, int pageNo, int pageSize,
			String sort);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findListByPageWithCondition(String itemCondition, int pageNo, int pageSize,
			String sort);

	/**
	 * 根据ID查找数据属性.
	 * 
	 * @param props
	 *            查询模型属性列表.
	 * @param id
	 *            ID
	 * @return 数据对象
	 */
	Object findProperties(List<String> props, Serializable id);

	/**
	 * 根据ID查找数据属性.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param id
	 *            ID
	 * @return 数据对象
	 */
	Object findProperties(String props, Serializable id);

	/**
	 * 根据ID查找数据(只查询一级数据).
	 * 
	 * @param id
	 *            ID
	 * @return 数据对象
	 */
	Object findWithSub(Serializable id);
	
	/**
	 * 根据ID查找数据(只查询maxSubLevel级数据).
	 * 
	 * @param id
	 *            ID
	 * @param maxSubLevel
	 *            最大子表层级
	 * @return 数据对象
	 */
	Object findWithSub(Serializable id, int maxSubLevel);

	/**
	 * 根据ID查找数据属性.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param ids
	 *            ID
	 * @return 数据对象
	 */
	List<?> findProperties(List<String> props, List<? extends Serializable> ids);

	/**
	 * 根据ID查找数据属性.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param ids
	 *            ID集合
	 * @return 数据对象
	 */
	List<?> findProperties(String props, List<? extends Serializable> ids);

	/**
	 * 根据ID查找数据带子表数据（只查询一级数据）.
	 * 
	 * @param ids
	 *            ID
	 * @return 数据对象
	 */
	List<?> findWithSub(List<? extends Serializable> ids);
	
	/**
	 * 根据ID查找数据带子表数据(只查询maxSubLevel级数据).
	 * 
	 * @param ids
	 *            ID
	 * @param maxSubLevel
	 *            最大子表层级
	 * @return 数据对象
	 */
	List<?> findWithSub(List<? extends Serializable> ids, int maxSubLevel);

	/**
	 * 条件分页查询带子表数据（只查询一级数据）.
	 * 
	 * @param itemCodition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findWithSubByPageWithCondition(List<ItemCondition> itemCodition, int pageNo,
			int pageSize);

	/**
	 * 条件分页查询带子表数据（只查询一级数据）(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findWithSubByPageWithCondition(String itemCondition, int pageNo, int pageSize);

	/**
	 * 条件分页排序查询带子表数据（只查询一级数据）.
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对，只支持主表字段象
	 * @return 分页查找结果
	 */
	PageModel<?> findWithSubByPageWithCondition(List<ItemCondition> itemCondition, int pageNo,
			int pageSize, String sort);
	
	/**
	 * 条件分页排序查询带子表数据(只查询maxSubLevel级数据).
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对，只支持主表字段象
	 * @param maxSubLevel
	 *           最大子表层级
	 * @return 分页查找结果
	 */
	PageModel<?> findWithSubByPageWithCondition(List<ItemCondition> itemCondition, int pageNo,
			int pageSize, String sort, int maxSubLevel);

	/**
	 * 条件分页查询带子表数据（只查询一级数据）(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 分页查找结果
	 */
	PageModel<?> findWithSubByPageWithCondition(String itemCondition, int pageNo, int pageSize,
			String sort);

	/**
	 * 条件查询带子表数据（只查询一级数据）.
	 * 
	 * @param itemCondition
	 *            主表查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findListWithSubByPageWithCondition(List<ItemCondition> itemCondition, int pageNo,
			int pageSize);

	/**
	 * 条件分页查询带子表数据（只查询一级数据）(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            主表查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findListWithSubByPageWithCondition(String itemCondition, int pageNo, int pageSize);

	/**
	 * 条件排序查询带子表数据（只查询一级数据）.
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findListWithSubByPageWithCondition(List<ItemCondition> itemCondition, int pageNo,
			int pageSize, String sort);
	
	/**
	 * 条件排序查询带子表数据(只查询maxSubLevel级数据).
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @param maxSubLevel
	 *           最大子表层级
	 * @return 数据列表
	 */
	List<?> findListWithSubByPageWithCondition(List<ItemCondition> itemCondition, int pageNo,
			int pageSize, String sort, int maxSubLevel);	

	/**
	 * 条件分页查询带子表数据（只查询一级数据）(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findListWithSubByPageWithCondition(String itemCondition, int pageNo, int pageSize,
			String sort);

	/**
	 * 条件分页查询.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCodition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findPropertiesByPageWithCondition(List<String> props, List<ItemCondition> itemCodition,
			int pageNo, int pageSize);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @return 分页查找结果
	 */
	PageModel<?> findPropertiesByPageWithCondition(List<String> props, String itemCondition, int pageNo,
			int pageSize);

	/**
	 * 条件分页排序查询.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段
	 * @return 分页查找结果
	 */
	PageModel<?> findPropertiesByPageWithCondition(String props, List<ItemCondition> itemCondition,
			int pageNo, int pageSize, String sort);	

	/**
	 * 条件分页排序查询.
	 * 
	 * @param props
	 *            查询模型属性列表.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段
	 * @return 分页查找结果
	 */
	PageModel<?> findPropertiesByPageWithCondition(List<String> props, List<ItemCondition> itemCondition,
			int pageNo, int pageSize, String sort);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            分页号
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 分页查找结果
	 */
	PageModel<?> findPropertiesByPageWithCondition(List<String> props, String itemCondition,
			int pageNo, int pageSize, String sort);

	/**
	 * 条件查询.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findPropertiesListByPageWithCondition(List<String> props, List<ItemCondition> itemCondition,
			int pageNo, int pageSize);

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @return 数据列表
	 */
	List<?> findPropertiesListByPageWithCondition(List<String> props, String itemCondition,
			int pageNo, int pageSize);

	/**
	 * 条件排序查询.
	 * 
	 * @param props
	 *            查询模型属性列表.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findPropertiesListByPageWithCondition(List<String> props, List<ItemCondition> itemCondition,
			int pageNo, int pageSize, String sort);

	/**
	 * 条件排序查询.
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findPropertiesListByPageWithCondition(String props, List<ItemCondition> itemCondition,
			int pageNo, int pageSize, String sort);	

	/**
	 * 条件分页查询(只处理 and条件，
	 * 其它情况使用findByFilter或findBySQL),格式：字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值
	 * " and 字段属性{@code "+"}空格{@code "+"}操作符{@code "+"}值"<br>
	 * "and"为条件分隔符, 样例：<br>
	 * field = 1 [等于]<br>
	 * field != 1 或 field {@code "<>"} 1[不等于]<br>
	 * field {@code ">"} 1 [大于] <br>
	 * field {@code "<"} 1 [小于]<br>
	 * field {@code ">="} 1 [大于等于]<br>
	 * field {@code "<="} 1 [小于等于]<br>
	 * field in (a,b,c) [包含]<br>
	 * field not_in (1,2,4) [不包含]<br>
	 * field like abc [like]<br>
	 * field not_like bcd [not like]<br>
	 * field is_null [is null]<br>
	 * field not_null [not null].<br>
	 * 
	 * @param props
	 *            查询模型属性列表，逗号分隔.
	 * @param itemCondition
	 *            查询过滤条件
	 * @param pageNo
	 *            页码
	 * @param pageSize
	 *            分页大小
	 * @param sort
	 *            排序对象，只支持主表字段，格式： 属性+空格+desc/asc 多条件排序逗号分隔，例如: field1 asc, field2 desc
	 * @return 数据列表
	 */
	List<?> findPropertiesListByPageWithCondition(List<String> props, String itemCondition,
			int pageNo, int pageSize, String sort);

}
