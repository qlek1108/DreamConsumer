package com.example.item.batch;


import com.example.item.db.Item;
import com.example.item.db.ItemRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
public class ItemTasklet implements Tasklet {

    private final ItemRepository itemRepository;

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("=====Start Change Board Status======");
        List<Item> items =itemRepository.findItemByDeletedIsTrue(System.currentTimeMillis());

//        List<Item> items = itemRepository.findAll();
        log.info("{}",items);
        log.info("{}",System.currentTimeMillis());
        if(items == null || items.isEmpty()) {
            log.info("=====변경할 게시글이 없습니다.=====");
//        } else {
//            LocalDate currentDate = LocalDate.now();
//            for(PracticeModel practiceModel : practiceModels){
//                LocalDate userDate = practiceModel.getDeletedTime();
//                Period period = Period.between(userDate, currentDate);
//                if (period.getDays() >= 31 || period.getMonths() >= 1) {
//                   praticeModelRepository.delete(practiceModel);
//                }
//            }
        }else {
            for(Item item : items) {
                itemRepository.delete(item);
            }


//            LocalDateTime currentDate = LocalDateTime.now();
//            for(PracticeModel practiceModel : practiceModels){
//                LocalDateTime userDate = practiceModel.getDeletedTime();
//                Duration duration = Duration.between(userDate, currentDate);
//                if (duration.toMinutes() >= 4) {
//                    praticeModelRepository.delete(practiceModel);
//                }
//            }

        }
        log.info("=====End Change Board Status======");
        return RepeatStatus.FINISHED;
    }
}
