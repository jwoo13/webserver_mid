package jungwoo.project_mid_20212092.service;

import jungwoo.project_mid_20212092.dao.FeedDAO;
import jungwoo.project_mid_20212092.domain.FeedVO;
import jungwoo.project_mid_20212092.dto.FeedDTO;

import jungwoo.project_mid_20212092.util.MapperUtil;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;


import java.util.List;
import java.util.stream.Collectors;

@Log4j2
public enum FeedService {

    INSTANCE;

    private FeedDAO dao;
    private ModelMapper modelMapper;

    FeedService() {
        dao = new FeedDAO();
        modelMapper = MapperUtil.INSTANCE.get();
    }

    public List<FeedDTO> listAll()throws Exception {

        List<FeedVO> voList = dao.selectAll();

        log.info("voList.................");
        log.info(voList);

        List<FeedDTO> dtoList = voList.stream()
                .map(vo -> modelMapper.map(vo, FeedDTO.class))
                .collect(Collectors.toList());

        return dtoList;
    }

    public void createFeed(FeedDTO feedDTO) throws Exception {
        FeedVO feedVO = modelMapper.map(feedDTO, FeedVO.class);

        log.info(feedVO);

        dao.insert(feedVO);
    }

    public FeedDTO getFeed(Long feedId)throws Exception {

        log.info("feedId: " + feedId);
        FeedVO feedVO = dao.selectOne(feedId);
        FeedDTO feedDTO = modelMapper.map(feedVO, FeedDTO.class);
        return feedDTO;
    }

    public void removeFeed(Long feedId)throws Exception {

        log.info("feedId: " + feedId);
        dao.deleteOne(feedId);
    }

    public void modifyFeed(FeedDTO feedDTO)throws Exception {

        log.info("feedDTO: " + feedDTO );

        FeedVO feedVO = modelMapper.map(feedDTO, FeedVO.class);

        dao.updateOne(feedVO);

    }

}