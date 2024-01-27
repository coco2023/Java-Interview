package demos;

import java.util.concurrent.atomic.AtomicInteger;

public class VotingItem {
          private Long id;
          private AtomicInteger voteCount; // 使用原子变量来跟踪投票计数
          
          // item.getVoteCount().incrementAndGet(); // 安全地更新投票计数

}
      