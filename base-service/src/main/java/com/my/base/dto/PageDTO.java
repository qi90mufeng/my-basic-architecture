package com.my.base.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页结构体
 *
 * @param <T>
 */
public class PageDTO<T> {
	private long total;
	private int pageNum;
	private int pageSize;
	private int pages;
	private List<T> list;
	private Map<String, Object> extension;
	public PageDTO() {
		
	}
	public PageDTO(List<T> list) {
		if (list instanceof com.github.pagehelper.Page) {
			com.github.pagehelper.Page<T> page = (com.github.pagehelper.Page<T>) list;
			this.total = page.getTotal();
            this.pageNum = page.getPageNum();
            this.pageSize = page.getPageSize();
            this.pages = page.getPages();
            this.list = page;
        }
		this.extension = new HashMap<>();
	}
	public long getTotal() {
		return total;
	}
	public void setTotal(long total) {
		this.total = total;
	}
	public int getPageNum() {
		return pageNum;
	}
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getPages() {
		return pages;
	}
	public void setPages(int pages) {
		this.pages = pages;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Map<String, Object> getExtension() {
		return extension;
	}
	public void setExtension(Map<String, Object> extension) {
		this.extension = extension;
	}
	public void putExtension(String key, Object value) {
		if (this.extension == null) {
			this.extension = new HashMap<>();
		}
		this.extension.put(key, value);
	}
	@Override
	public String toString() {
		return "PageDTO [total=" + total + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", pages=" + pages
				+ ", list=" + list + ", extension=" + extension + "]";
	}
}
