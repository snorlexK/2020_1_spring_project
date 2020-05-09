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
public class User {
	private String id;
	private String password;
	private String nickname;
	private String location1;
	private String location2;
	private Date registeredDate;
}
