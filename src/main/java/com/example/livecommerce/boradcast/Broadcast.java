package com.example.livecommerce.boradcast;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Broadcast {
	@Id
	private long id;

	@Column(columnDefinition = "TEXT")
	private String productName;
	private String broadcastTitle;
	private String images;
	private String channelId;
	private String category;
	private String productUri;
	private int price;
	private boolean state;

	@OneToMany
	@JoinColumn(name = "broadcastId")
	private List<BroadcastFile> files;
}
