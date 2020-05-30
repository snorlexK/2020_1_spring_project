package itc.hoseo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private int id;
	private String name;
	private String category;
	private int price;
	private String description;
	private Date uploadDate;
	private Date updateDate;
	private String userId;
	private String location1;
	private String location2;
	private Date soldDate;
	private String image;
}
