package org.hdj.ssm.po;

import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hdj.ssm.controller.validation.GroupValidate1;
import org.hdj.ssm.controller.validation.GroupValidate2;



public class items {
    private Integer id;
 
    @Size(min=2,max=5,message="{item.name.length.error}",groups={GroupValidate1.class})
    private String name;
    
    
    private Float price;
    
   
    private String pic;
    
    @NotNull(message="{item.createtime.isNull}",groups={GroupValidate2.class})
    private Date createtime;


    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
    	System.out.println(System.currentTimeMillis()+" ::get: "+name );
        return name;
    }

    public void setName(String name) {
    	System.out.println(System.currentTimeMillis()+" :set:: "+name);
        this.name = name == null ? null : name.trim();
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic == null ? null : pic.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}