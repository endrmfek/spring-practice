package hoteldelluna.springweb.boot.controller;

import hoteldelluna.springweb.boot.dto.BootChangeProductNameDto;
import hoteldelluna.springweb.boot.dto.BootProductDto;
import hoteldelluna.springweb.boot.dto.BootProductResponseDto;
import hoteldelluna.springweb.boot.service.BootProductService;
import io.swagger.models.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/boot/product")
public class BootProductController {

    private final BootProductService productService;

    public BootProductController(BootProductService productService) {
        this.productService = productService;
    }

    @GetMapping()
    public ResponseEntity<BootProductResponseDto> getProduct(Long number) {
        BootProductResponseDto productResponseDto = productService.getProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PostMapping()
    public ResponseEntity<BootProductResponseDto> createProduct(@RequestBody BootProductDto productDto) {
        BootProductResponseDto productResponseDto = productService.saveProduct(productDto);

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @PutMapping()
    public ResponseEntity<BootProductResponseDto> changeProductName(
            @RequestBody BootChangeProductNameDto changeProductNameDto) throws Exception {
        BootProductResponseDto productResponseDto = productService.changeProductName(
                changeProductNameDto.getNumber(),
                changeProductNameDto.getName());

        return ResponseEntity.status(HttpStatus.OK).body(productResponseDto);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteProduct(Long number) throws Exception {
        productService.deleteProduct(number);

        return ResponseEntity.status(HttpStatus.OK).body("정상적으로 삭제되었습다.");
    }
}
