package app;

public class Brand {
  private Integer id;
  private String brand;
  private BrandDescription brandDescription;

  public Brand() {
  }

  public Brand(String brand, BrandDescription brandDescription) {
    this.brand = brand;
    this.brandDescription = brandDescription;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getBrand() {
    return brand;
  }

  public void setBrand(String brand) {
    this.brand = brand;
  }

  public BrandDescription getBrandDescription() {
    return brandDescription;
  }

  public void setBrandDescription(BrandDescription brandDescription) {
    this.brandDescription = brandDescription;
  }
}
