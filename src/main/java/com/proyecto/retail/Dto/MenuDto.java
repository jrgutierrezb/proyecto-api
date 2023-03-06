package com.proyecto.retail.Dto;

import java.util.List;

public class MenuDto {
	
	private Integer id;

	private String name;
	
	private String url;
	
	private IconDto iconComponent;
	
	private List<MenuDto> children;
	
	private List<String> permissions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public IconDto getIconComponent() {
		return iconComponent;
	}

	public void setIconComponent(IconDto iconComponent) {
		this.iconComponent = iconComponent;
	}

	public List<MenuDto> getChildren() {
		return children;
	}

	public void setChildren(List<MenuDto> children) {
		this.children = children;
	}

	public List<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(List<String> permissions) {
		this.permissions = permissions;
	}
	
}