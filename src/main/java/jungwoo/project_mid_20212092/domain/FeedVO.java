package jungwoo.project_mid_20212092.domain;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class FeedVO {

    private Long feedId;
    private String content;
    private LocalDateTime modifiedDate;

}

