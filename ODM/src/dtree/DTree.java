package dtree;

import java.util.ArrayList;
import java.util.List;
/** ����*/
public class DTree {
  /** �ڵ�ID*/
  private String id;
  /** �ϼ��ڵ�ID*/
  private String parentId;
  /** �ڵ�����*/
  private String title;
  /** �Ƿ�չ���ڵ�*/
  private Boolean spread;
  /** �Ƿ����һ���ڵ�*/
  private Boolean last;
  /** �Ƿ�����*/
  private Boolean hide;
  /** �Ƿ����*/
  private Boolean disabled;
  /** �Զ���ͼ��class*/
  private String iconClass;
  /** ��ʾ�û��Զ�����Ҫ�洢�����ڵ��е�����*/
  private Object basicData;
  /** ��ѡ�򼯺�*/
  private List<CheckArr> checkArr = new ArrayList<CheckArr>();
  /** �ӽڵ㼯��*/
  private List<DTree> children = new ArrayList<DTree>();
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Boolean getSpread() {
		return spread;
	}
	public void setSpread(Boolean spread) {
		this.spread = spread;
	}
	public Boolean getLast() {
		return last;
	}
	public void setLast(Boolean last) {
		this.last = last;
	}
	public Boolean getHide() {
		return hide;
	}
	public void setHide(Boolean hide) {
		this.hide = hide;
	}
	public Boolean getDisabled() {
		return disabled;
	}
	public void setDisabled(Boolean disabled) {
		this.disabled = disabled;
	}
	public String getIconClass() {
		return iconClass;
	}
	public void setIconClass(String iconClass) {
		this.iconClass = iconClass;
	}
	public Object getBasicData() {
		return basicData;
	}
	public void setBasicData(Object basicData) {
		this.basicData = basicData;
	}
	public List<CheckArr> getCheckArr() {
		return checkArr;
	}
	public void setCheckArr(List<CheckArr> checkArr) {
		this.checkArr = checkArr;
	}
	public List<DTree> getChildren() {
		return children;
	}
	public void setChildren(List<DTree> children) {
		this.children = children;
	}
  
}

