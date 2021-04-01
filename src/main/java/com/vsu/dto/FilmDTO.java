package com.vsu.dto;

import com.vsu.Models.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Set;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FilmDTO {
    private Long id;
    private String name;
    private LocalDate date;
    private Byte[] image;
    private Type type;
    private Country country;
    private Double rating;
    private Integer budget;
    private Integer duration;
    private Set<Genre> genres;
    private Set<FilmStaff> filmStaffs;
    private List<Grade> grades;
    private List<Review> reviews;
    public String getStringImg() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(1000);
        BufferedImage image = ImageIO.read(new File("src/main/resources/static/images/defaultImage.png"));
        ImageIO.write(image, "png", baos);
        baos.flush();
        String img = Base64.getMimeEncoder().encodeToString(baos.toByteArray());
        baos.close();
        return img;
    }
}
