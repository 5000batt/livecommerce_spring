package com.example.livecommerce.boradcast;

import javax.servlet.http.HttpServletResponse;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class BroadService {

	private BroadcastRepository broadcastRepo;
	private BroadcastFileRepository broadcastfileRepo;

	public BroadService(BroadcastRepository broadcastrepo, BroadcastFileRepository broadcastfilerepo) {
		this.broadcastRepo = broadcastrepo;
		this.broadcastfileRepo = broadcastfilerepo;
	}

	@RabbitListener(queues = "commerce.broadcast")
	public void receiveBroadcast(Broadcast broadcast) {
		System.out.println("---- BROADCAST LOG ----");
		System.out.println(broadcast);
		System.out.println(broadcast.getId());
		System.out.println(broadcastRepo.findById(broadcast.getId()).isEmpty());
//		System.out.println(broadcast.getFiles());
//		System.out.println(broadcast.getFiles().get(0));

		if (broadcastRepo.findById(broadcast.getId()).isEmpty() == false) {

			Broadcast modBroadcast = broadcastRepo.findById(broadcast.getId()).orElse(null);

			if (modBroadcast == null) {
				return;
			}

			modBroadcast.setBroadcastTitle(broadcast.getBroadcastTitle());
			modBroadcast.setPrice(broadcast.getPrice());
			modBroadcast.setCategory(broadcast.getCategory());
			modBroadcast.setChannelId(broadcast.getChannelId());
			modBroadcast.setProductName(broadcast.getProductName());
			modBroadcast.setImages(broadcast.getImages());

			if (modBroadcast.isState() == true) {
				modBroadcast.setState(false);
			}

			else if (modBroadcast.isState() == false) {
				modBroadcast.setState(true);
			}

			broadcastRepo.save(modBroadcast);
		}

		if (broadcastRepo.findById(broadcast.getId()).isEmpty() == true) {
			Broadcast addbroadcast = Broadcast.builder().broadcastTitle(broadcast.getBroadcastTitle())
					.category(broadcast.getCategory()).channelId(broadcast.getChannelId()).images(broadcast.getImages())
					.price(broadcast.getPrice()).productName(broadcast.getProductName()).id(broadcast.getId())
					.productUri(broadcast.getProductUri()).state(true).build();

			broadcastRepo.save(addbroadcast);

			broadcastfileRepo.save(broadcast.getFiles().get(0));
		}
	}

}
