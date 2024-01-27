package demos;

import java.util.concurrent.locks.ReentrantLock;

@Service
public class VotingService {
    private final ReentrantLock lock = new ReentrantLock();
    
    @Autowired
    private VotingItemRepository votingItemRepository;

    public boolean castVote(Long itemId) {
        lock.lock(); // 为确保数据一致性获取锁
        try {
            VotingItem item = votingItemRepository.findById(itemId).orElseThrow();
            item.getVoteCount().incrementAndGet(); // 安全地更新投票计数
            votingItemRepository.save(item); // 将更新后的计数保存到数据库
        } finally {
            lock.unlock(); // 释放锁
        }
        return true;
    }
}
