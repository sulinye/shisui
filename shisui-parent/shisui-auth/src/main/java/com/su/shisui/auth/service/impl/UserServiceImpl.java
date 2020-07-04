package com.su.shisui.auth.service.impl;

import com.su.shisui.api.auth.service.UserService;
import com.su.shisui.auth.common.po.ImageCode;
import com.su.shisui.auth.common.po.User;
import com.su.shisui.auth.common.vo.UserVo;
import com.su.shisui.auth.mapper.UserMapper;
import com.su.shisui.common.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Random;

/**
 * author sly
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User userNew = userMapper.findUser(user);
        return userNew;
    }

    @Override
    public ImageCode generateCode() throws IOException {
        final int width = 100;
        final int height = 32;
        final BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);

        final Graphics g = image.getGraphics();

        final Random random = new Random();

        g.setColor(getRandColor(200, 250));
        g.fillRect(0, 0, width, height);
        g.setFont(new Font("Times New Roman", Font.ITALIC, 20));
        g.setColor(getRandColor(160, 200));
        for (int i = 0; i < 155; i++) {
            final int x = random.nextInt(width);
            final int y = random.nextInt(height);
            final int xl = random.nextInt(12);
            final int yl = random.nextInt(12);
            g.drawLine(x, y, x + xl, y + yl);
        }

        String sRand = "";
        for (int i = 0; i < 4; i++) {
            final String rand = String.valueOf(random.nextInt(10));
            sRand += rand;
            g.setColor(new Color(20 + random.nextInt(110), 20 + random.nextInt(110), 20 + random.nextInt(110)));
            g.drawString(rand, 13 * i + 24, 24);
        }

        g.dispose();


        final ByteArrayOutputStream os = new ByteArrayOutputStream();

        ImageIO.write(image, "png", os);

        final ByteArrayInputStream inputStream = new ByteArrayInputStream(os.toByteArray());
        // 将二进制修改为base64，前面加上"data:image/png;base64,"，得到图片的src
        final ByteArrayOutputStream bos = new ByteArrayOutputStream();
        final byte[] b1 = new byte[1024];
        int len = -1;
        while((len = inputStream.read(b1)) != -1) {
            bos.write(b1, 0, len);
        }
        final byte[] fileByte = bos.toByteArray();

        final BASE64Encoder encoder = new BASE64Encoder(); // import sun.misc.BASE64Encoder;
        // 对字节数组转换成Base64字符串
        final String base64String = encoder.encode(fileByte);
        inputStream.close();
        image.flush();
        os.close();

        return new ImageCode(base64String, sRand, 60);
    }

    @Override
    public User findUserById(Long id) {
        User user = new User();
        user.setId(id.toString());
        return userMapper.findUser(user);
    }

    /**
     * 生成随机背景条纹
     *
     * @param fc
     * @param bc
     * @return
     */
    private Color getRandColor(int fc, int bc) {
        final Random random = new Random();
        if (fc > 255) {
            fc = 255;
        }
        if (bc > 255) {
            bc = 255;
        }
        final int r = fc + random.nextInt(bc - fc);
        final int g = fc + random.nextInt(bc - fc);
        final int b = fc + random.nextInt(bc - fc);
        return new Color(r, g, b);
    }
}
