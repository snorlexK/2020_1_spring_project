package itc.hoseo.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import itc.hoseo.Product;
import itc.hoseo.ProductRepository;
import itc.hoseo.User;
import itc.hoseo.UserRepository;

@Component
public class ProductConverter implements Converter<Long, Product> {
	
	@Autowired
	private ProductRepository repo;

	@Override
	public Product convert(Long value) {
		return repo.findById(value).get();
	}

}
