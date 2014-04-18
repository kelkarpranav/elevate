package app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriTemplate;

import java.net.URI;
import java.util.List;

@Controller
@RequestMapping("/brands")
public class BrandController {

  @Autowired
  BrandRepository brandRepository;

  @RequestMapping(method = RequestMethod.GET)
  public @ResponseBody List<Brand> list() {
    return this.brandRepository.findAll();
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.GET)
  public @ResponseBody Brand find(@PathVariable("id") Integer id) {
	  Brand brand = this.brandRepository.findById(id);
    if (brand == null) {
      throw new BrandNotFoundException(id);
    }
    return brand;
  }

  @RequestMapping(method = RequestMethod.POST, consumes = {"application/json"})
  @ResponseStatus(HttpStatus.CREATED)
  public HttpEntity<?> create(@RequestBody Brand brand, @Value("#{request.requestURL}") StringBuffer parentUri) {
	  brand = this.brandRepository.save(brand);
    HttpHeaders headers = new HttpHeaders();
    headers.setLocation(childLocation(parentUri, brand.getId()));
    return new HttpEntity<Object>(headers);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable("id") Integer id) {
    this.brandRepository.delete(id);
  }

  @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void update(@PathVariable("id") Integer id, @RequestBody Brand brand) {
	  brand.setId(id);
    this.brandRepository.save(brand);
  }


  private URI childLocation(StringBuffer parentUri, Object childId) {
    UriTemplate uri = new UriTemplate(parentUri.append("/{childId}").toString());
    return uri.expand(childId);
  }

  @ResponseStatus(HttpStatus.NOT_FOUND)
  public class BrandNotFoundException extends RuntimeException {
    public BrandNotFoundException(Integer id) {
      super("Brand '" + id + "' not found.");
    }
  }
}
