package com.smbms.pojo;

public class Page {
		private int size;//每页信息条数
		private int totalCount;//总信息条数
		private int totalPage;//总页数
		private int currentpage;//当前页
		public int getSize() {
			return size;
		}
		public void setSize(int size) {
			this.size = size;
		}
		public int getTotalCount() {
			return totalCount;
		}
		public void setTotalCount(int totalCount) {
			this.totalCount = totalCount;
			if(totalCount%size==0){
				this.totalPage=totalCount/size;
			}else{
				this.totalPage=totalCount/size+1;
			}
		}
		public int getTotalPage() {
			return totalPage;
		}
		public int getCurrentpage() {
			return currentpage;
		}
		public void setCurrentpage(int currentpage) {
			this.currentpage = currentpage;
		}
		
}
