package jungwoo.project_mid_20212092.dto;


import lombok.*;

import java.time.LocalDateTime;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FeedDTO {

    private Long feedId;
    private String content;
    private LocalDateTime modifiedDate;

}
