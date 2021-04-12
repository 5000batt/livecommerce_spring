package com.example.livecommerce.boradcast;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BroadcastController {

	private BroadcastRepository broadcastRepo;

	@Autowired
	private BroadcastController(BroadcastRepository broadcastRepo) {
		this.broadcastRepo = broadcastRepo;

	}

	// 방송조회
	@RequestMapping(value = "/broadcasts", method = RequestMethod.GET)
	public List<Broadcast> getBroadcast() {
		return broadcastRepo.findByState(true);
	}

	// 방송수정
	@RequestMapping(value = "/broadcasts/{id}", method = RequestMethod.PATCH)
	public Broadcast modifyBroadcast(@PathVariable("id") long id, @RequestBody Broadcast broadcast,
			HttpServletResponse res) {

		Broadcast modBroadcast = broadcastRepo.findById(id).orElse(null);

		if (modBroadcast == null) {
			res.setStatus(HttpServletResponse.SC_NOT_FOUND);
			return null;
		}

		modBroadcast.setBroadcastTitle(broadcast.getBroadcastTitle());
		modBroadcast.setPrice(broadcast.getPrice());
		modBroadcast.setCategory(broadcast.getCategory());
		modBroadcast.setChannelId(broadcast.getChannelId());
		modBroadcast.setProductName(broadcast.getProductName());
		modBroadcast.setImages(broadcast.getImages());
		modBroadcast.setFiles(broadcast.getFiles());
//			System.out.println(modBroadcast);

		broadcastRepo.save(modBroadcast);

		return modBroadcast;
	}
}
