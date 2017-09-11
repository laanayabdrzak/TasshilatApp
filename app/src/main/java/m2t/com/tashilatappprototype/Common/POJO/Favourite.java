package m2t.com.tashilatappprototype.Common.POJO;

public class Favourite {

	public int icon;
	public String title;

	public Favourite(String title, int icon) {
		this.title = title;
		this.icon = icon;
	}

	public int getIcon() {
		return icon;
	}

	public void setIcon(int icon) {
		this.icon = icon;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
