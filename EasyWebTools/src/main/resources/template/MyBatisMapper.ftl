<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<#import "/Common.ftl" as myFun>
<#assign tableName>
	<@compress single_line=true>
		<@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>
	</@compress>
</#assign>
<#assign entityName = tableName>
<#assign searchEntityName = tableName + "Search">
<#macro dbType type><#if type?upper_case='INT'>INTEGER<#elseif type?upper_case='DATETIME'>TIMESTAMP<#elseif type?upper_case='TEXT'>VARCHAR<#else>${type}</#if></#macro>
<mapper namespace="${daoPackageInfo}.<@myFun.name2HumpFormat name=oDBTableInfo.tableName isFirstUpper=true/>Dao">
	<select id="selectPK" parameterType="${searchEntityName}" resultType="${entityName}">
		SELECT
		<#list oDBTableInfo.columns as oDBColumnInfo>
			${oDBColumnInfo.columnName}<#if oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>,
			<#if oDBColumnInfo.extendInfo.referenceType=='table'>
			(SELECT ${oDBColumnInfo.extendInfo.referenceName} FROM ${oDBColumnInfo.extendInfo.referenceTable} t WHERE t.${oDBColumnInfo.extendInfo.referenceKey}=${oDBTableInfo.tableName}.${oDBColumnInfo.columnName}) AS ${oDBColumnInfo.columnName}Str
			<#elseif oDBColumnInfo.extendInfo.referenceType=='dictionary'>
			(SELECT item_value FROM system_dictionary_value WHERE dic_code='${oDBColumnInfo.extendInfo.condition}' AND system_dictionary_value.item_key=${oDBTableInfo.tableName}.${oDBColumnInfo.columnName}) AS ${oDBColumnInfo.columnName}Str
			</#if>
			</#if><#if oDBColumnInfo_has_next>,</#if>
		</#list>
		FROM ${oDBTableInfo.tableName}
		<#if oDBTableInfo.primaryKey?size &gt; 0>
		WHERE <#list oDBTableInfo.primaryKey?keys as key>${oDBTableInfo.primaryKey[key].columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBTableInfo.primaryKey[key].columnName/>,jdbcType=<@dbType type=oDBTableInfo.primaryKey[key].typeName/>}<#if key_has_next> AND </#if></#list>
		</#if>
	</select>

	<select id="select" parameterType="${searchEntityName}" resultType="${entityName}">
		SELECT
		<#list oDBTableInfo.columns as oDBColumnInfo>
			${oDBColumnInfo.columnName}<#if oDBColumnInfo.extendInfo.constraint?? && oDBColumnInfo.extendInfo.constraint?lower_case=='true'>,
			<#if oDBColumnInfo.extendInfo.referenceType=='table'>
			(SELECT ${oDBColumnInfo.extendInfo.referenceName} FROM ${oDBColumnInfo.extendInfo.referenceTable} t WHERE t.${oDBColumnInfo.extendInfo.referenceKey}=${oDBTableInfo.tableName}.${oDBColumnInfo.columnName}) AS ${oDBColumnInfo.columnName}Str
			<#elseif oDBColumnInfo.extendInfo.referenceType=='dictionary'>
			(SELECT item_value FROM system_dictionary_value WHERE dic_code='${oDBColumnInfo.extendInfo.condition}' AND system_dictionary_value.item_key=${oDBTableInfo.tableName}.${oDBColumnInfo.columnName}) AS ${oDBColumnInfo.columnName}Str
			</#if>
			</#if><#if oDBColumnInfo_has_next>,</#if>
		</#list>
		FROM ${oDBTableInfo.tableName}
		<trim prefix="WHERE" prefixOverrides="AND">
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin != ''">
				AND ${oDBColumnInfo.columnName} &gt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd != ''">
				AND ${oDBColumnInfo.columnName} &lt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
		<#elseif oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin != null">
				AND ${oDBColumnInfo.columnName} &gt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd != null">
				AND ${oDBColumnInfo.columnName} &lt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
		<#elseif oDBColumnInfo.typeName == "VARCHAR" || oDBColumnInfo.typeName == "TEXT">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != ''">
				AND ${oDBColumnInfo.columnName} LIKE CONCAT('%', <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}, '%')
			</if>
		<#else>
			<#--oDBColumnInfo.typeName == "DOUBLE" || oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT"-->
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != ''">
				AND ${oDBColumnInfo.columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
		</#if>
		</#list>
		</trim>
		LIMIT <#noparse>#{</#noparse>offset}, <#noparse>#{</#noparse>rows}
	</select>

	<select id="selectCount" parameterType="${searchEntityName}" resultType="java.lang.Long">
		SELECT COUNT(1)
		FROM ${oDBTableInfo.tableName}
		<trim prefix="WHERE" prefixOverrides="AND">
		<#list oDBTableInfo.columns as oDBColumnInfo>
		<#if oDBColumnInfo.extendInfo.realTypeName?? && oDBColumnInfo.extendInfo.realTypeName?lower_case=='date'>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin != null">
				AND ${oDBColumnInfo.columnName} &gt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd != null">
				AND ${oDBColumnInfo.columnName} &lt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
		<#elseif oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP" || oDBColumnInfo.typeName == "DOUBLE" || oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin != null">
				AND ${oDBColumnInfo.columnName} &gt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchBegin,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd != null">
				AND ${oDBColumnInfo.columnName} &lt;= <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>SearchEnd,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
		<#elseif oDBColumnInfo.typeName == "VARCHAR" || oDBColumnInfo.typeName == "TEXT">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != ''">
				AND ${oDBColumnInfo.columnName} LIKE CONCAT('%', <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}, '%')
			</if>
		<#else>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != ''">
				AND ${oDBColumnInfo.columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
		</#if>
		</#list>
		</trim>
	</select>

	<insert id="insert" parameterType="${entityName}">
		INSERT INTO ${oDBTableInfo.tableName}
		(
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.isNullAbled()>
				<#if oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP" || oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null">
				<#else>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != ''">
				</#if>
			<#if oDBColumnInfo_index != 0>,</#if>${oDBColumnInfo.columnName}
			</if>
			<#else>
			<#if oDBColumnInfo_index != 0>,</#if>${oDBColumnInfo.columnName}
			</#if>
		</#list>
		)
		VALUES
		(
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.isNullAbled()>
				<#if oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP" || oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null">
				<#else>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null and <@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != ''">
				</#if>
			<#if oDBColumnInfo_index != 0>,</#if><#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
			<#else>
			<#if oDBColumnInfo_index != 0>,</#if><#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</#if>
		</#list>
		)
	</insert>

	<update id="update" parameterType="${entityName}">
		UPDATE ${oDBTableInfo.tableName} SET
		<#list oDBTableInfo.columns as oDBColumnInfo>
			<#if oDBColumnInfo.isNullAbled()>
				<#if oDBColumnInfo.typeName == "DATE" || oDBColumnInfo.typeName == "DATETIME" || oDBColumnInfo.typeName == "TIMESTAMP" || oDBColumnInfo.typeName == "INTEGER" || oDBColumnInfo.typeName == "INT">
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null">
				<#else>
			<if test="<@myFun.name2HumpFormat name=oDBColumnInfo.columnName/> != null">
				</#if>
			<#if oDBColumnInfo_index != 0>,</#if>${oDBColumnInfo.columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</if>
			<#else>
			<#if oDBColumnInfo_index != 0>,</#if>${oDBColumnInfo.columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBColumnInfo.columnName/>,jdbcType=<@dbType type=oDBColumnInfo.typeName/>}
			</#if>
		</#list>
		WHERE <#list oDBTableInfo.primaryKey?keys as key>${oDBTableInfo.primaryKey[key].columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBTableInfo.primaryKey[key].columnName/>,jdbcType=<@dbType type=oDBTableInfo.primaryKey[key].typeName/>}<#if key_has_next> AND </#if></#list>
	</update>

	<delete id="delete" parameterType="${entityName}">
		DELETE FROM ${oDBTableInfo.tableName}
		WHERE <#list oDBTableInfo.primaryKey?keys as key>${oDBTableInfo.primaryKey[key].columnName} = <#noparse>#{</#noparse><@myFun.name2HumpFormat name=oDBTableInfo.primaryKey[key].columnName/>,jdbcType=<@dbType type=oDBTableInfo.primaryKey[key].typeName/>}<#if key_has_next> AND </#if></#list>
	</delete>

	<delete id="deleteList" parameterType="java.util.List">
		DELETE FROM ${oDBTableInfo.tableName}
		WHERE
		<foreach collection="list" item="entity" index="index" open="(" separator=" OR " close=")">
			(<#list oDBTableInfo.primaryKey?keys as key>${oDBTableInfo.primaryKey[key].columnName} = <#noparse>#{</#noparse>entity.<@myFun.name2HumpFormat name=oDBTableInfo.primaryKey[key].columnName/>,jdbcType=<@dbType type=oDBTableInfo.primaryKey[key].typeName/>}<#if key_has_next> AND </#if></#list>)
		</foreach>
	</delete>
</mapper>