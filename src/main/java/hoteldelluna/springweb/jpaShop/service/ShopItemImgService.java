package hoteldelluna.springweb.jpaShop.service;

import hoteldelluna.springweb.jpaShop.Repository.ShopItemImgRepository;
import hoteldelluna.springweb.jpaShop.entity.ShopItemImg;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;

@Service
@RequiredArgsConstructor
@Transactional
public class ShopItemImgService {

    @Value("${itemImgLocation}")
    private String itemImgLocation;

    private final ShopItemImgRepository itemImgRepository;

    private final ShopFileService fileService;

    public void saveItemImg(ShopItemImg itemImg, MultipartFile itemImgFile) throws Exception {
        String oriImgName = itemImgFile.getOriginalFilename(); //원래 파일 이름
        String imgName = ""; //실제로컬에 저장된 파일이름 -> UUID + oriImgName
        String imgUrl = ""; // 로컬에 저장된 파일 불러오는 경로.

        //파일 업로드
        if(!StringUtils.isEmpty(oriImgName)) {
            imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
        }

        itemImg.updateItemImg(oriImgName,imgName,imgUrl);
        itemImgRepository.save(itemImg);
    }

    public void updateItemImg(Long itemImgId, MultipartFile itemImgFile) throws Exception {
        if(!itemImgFile.isEmpty()) {
            ShopItemImg savedItemImg = itemImgRepository.findById(itemImgId)
                    .orElseThrow(EntityNotFoundException::new);

            //기존 이미지 삭제
            if(!StringUtils.hasText(savedItemImg.getImgName())) {
                fileService.deleteFile(itemImgLocation+"/"+savedItemImg.getImgName());
            }
            String oriImgName = itemImgFile.getOriginalFilename();
            String imgName = fileService.uploadFile(itemImgLocation, oriImgName, itemImgFile.getBytes());
            String imgUrl = "/images/item/" + imgName;
            savedItemImg.updateItemImg(oriImgName, imgName, imgUrl);
        }
    }
}
