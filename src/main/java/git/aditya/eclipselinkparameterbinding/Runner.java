package git.aditya.eclipselinkparameterbinding;

import git.aditya.eclipselinkparameterbinding.persistence.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
public class Runner implements CommandLineRunner {
    @Autowired
    MessageRepository messageRepository;

    @Override
    public void run(String... args) throws Exception {
        System.out.println(messageRepository.getMessage());
    }
}
