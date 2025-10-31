package domainobjects;

public class OrderNote {
  private String note;

  public OrderNote() {

  }
  public OrderNote(String note) {
    this.note = note;
  }

  public String getNote() { return note; }

  public void setNote(String note) {
    this.note = note;
  }
}