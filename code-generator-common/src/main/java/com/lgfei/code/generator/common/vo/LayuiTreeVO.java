package com.lgfei.code.generator.common.vo;

import java.io.Serializable;
import java.util.List;

/**
 * layui树形对象
 * @author long.gf
 *
 */
public class LayuiTreeVO implements Serializable{

    private static final long serialVersionUID = 1L;

    private String id;
    
    private String title;
    
    private boolean spread;
    
    private boolean checked;
    
    List<LayuiTreeVO> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isSpread() {
        return spread;
    }

    public void setSpread(boolean spread) {
        this.spread = spread;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public List<LayuiTreeVO> getChildren() {
        return children;
    }

    public void setChildren(List<LayuiTreeVO> children) {
        this.children = children;
    }
    
}
