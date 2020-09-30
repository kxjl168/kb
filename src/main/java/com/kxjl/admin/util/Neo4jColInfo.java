package com.kxjl.admin.util;

import java.util.ArrayList;
import java.util.List;

//neo4j导出表头数据
public class Neo4jColInfo {
	private List<String> cols=new ArrayList<>();
	private int colRelationStart = 0; //_start
	private int colNodeId=0;//编码
	private int col_id=0;//_id
	private int col_tags=1;//_labels 标签  :xx:xxx
	private int col_name=0;//名称
	
	private int col_last=0;//最后一列
	
	public int getColRelationStart() {
		return colRelationStart;
	}
	public void setColRelationStart(int colRelationStart) {
		this.colRelationStart = colRelationStart;
	}
	public int getColNodeId() {
		return colNodeId;
	}
	public void setColNodeId(int colNodeId) {
		this.colNodeId = colNodeId;
	}
	public int getCol_id() {
		return col_id;
	}
	public void setCol_id(int col_id) {
		this.col_id = col_id;
	}
	public int getCol_tags() {
		return col_tags;
	}
	public void setCol_tags(int col_tags) {
		this.col_tags = col_tags;
	}
	public int getCol_name() {
		return col_name;
	}
	public void setCol_name(int col_name) {
		this.col_name = col_name;
	}
	public int getCol_last() {
		return col_last;
	}
	public void setCol_last(int col_last) {
		this.col_last = col_last;
	}
	public List<String> getCols() {
		return cols;
	}
	public void setCols(List<String> cols) {
		this.cols = cols;
	}
}
