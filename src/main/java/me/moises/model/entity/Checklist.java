package me.moises.model.entity;

import java.time.LocalDate;
import java.util.UUID;

import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Checklist {
	@Id
	@SequenceGenerator(initialValue = 2000001, allocationSize = 1, name = "id_checklist_gen")
	@GeneratedValue(generator = "id_checklist_gen", strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDate deathDate;
	private String token;
	@ManyToOne
	@JoinColumn(name ="user_id", foreignKey = @ForeignKey(name = "id"))
	private User user;
	
	public Checklist(User user) {
		this.user = user;
		this.token = UUID.randomUUID().toString();
		this.deathDate = getExpiresDate();
	}
	
	private LocalDate getExpiresDate() {
		LocalDate date = LocalDate.now();	
		if (((date.getDayOfMonth()+3) / date.lengthOfMonth()) > 1)
			date = LocalDate.of(date.getYear(), date.getMonth().getValue() + 1, (date.getDayOfMonth() + 3) - date.getMonth().getValue());
		else
			date = LocalDate.of(date.getYear(), date.getMonth().getValue(), date.getDayOfMonth() + 3);
		return date;
	}
	
}
