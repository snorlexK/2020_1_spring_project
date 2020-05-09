package itc.hoseo;

import java.sql.Timestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Product {
	private String id;
	private String name;
	private String category;
	private int price;
	private String description;
	private Timestamp uploadDate;
	private Timestamp soldDate;
	private String image;
	private String userId;
}
