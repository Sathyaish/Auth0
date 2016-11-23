import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import javax.swing.*;

/**
 * Created by computer on 9/7/2016.
 */
public class Program {

    public static void main(String[] args) {

        Jws<Claims> jws = null;
        ObjectMapper mapper = new ObjectMapper();

        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                JDialog dialog = new JDialog();
                dialog.setTitle("New Window");
                dialog.setVisible(true);
            }
        });
    }
}
