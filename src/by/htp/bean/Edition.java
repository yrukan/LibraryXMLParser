package by.htp.bean;

public class Edition {
	private int id;
	private String type;
	private String name;
	private String author;
	private int year;
	private String readingPlace;
	private int readingTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public String getReadingPlace() {
		return readingPlace;
	}

	public void setReadingPlace(String readingPlace) {
		this.readingPlace = readingPlace;
	}

	public int getReadingTime() {
		return readingTime;
	}

	public void setReadingTime(int readingTime) {
		this.readingTime = readingTime;
	}

	public String toString() {
		return "Edition [id=" + id + ", type=" + type + ", name=" + name + ", author=" + author + ", year=" + year
				+ ", readingPlace=" + readingPlace + ", readingTime=" + readingTime + "]";
	}

}
