package com.zcy.restaurant.entity;


import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

/**
 * @ Author: chunyang.zhang
 * @ Description: 类目 (产品种类) 、  对应java pojo类
 * @ Date: Created in 20:59
 * @ Modified: By:
 */

@Entity
@DynamicUpdate  //动态更新，数据库自动更改的(如：时间),即使映射对象没有更改，也会动态的自动更改
@Data  //省去写get、set的代码，实现一样的工程（lombok依赖，需要装插件。(龙目岛印尼某岛)）
       //同等替换还有，@Getter @Setter注解，只实现get or set方法
public class ProductCategory {

    /** 类目id. */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //自增 , 不加GenerationType单元测试报错
    private Integer categoryId;

    /** 类目名字. */
    private String categoryName;

    /** 类目编号 */
    private Integer categoryType;

    private Date createTime;

    private Date updateTime;


    public ProductCategory() {
    }

    public ProductCategory(String categoryName, Integer categoryType) {
        this.categoryName = categoryName;
        this.categoryType = categoryType;
    }
}
