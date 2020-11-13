package sample.animations;

// формочки которые будут трястись если что то неправильно ввели

import javafx.animation.TranslateTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class Shake
{
    private final TranslateTransition translateTransition;

    // Node - любой объект на нашем окне (кнопка, надпис и т.д.)
    public Shake(Node node) {
        // сколько будет трястись наш объект
        translateTransition = new TranslateTransition(Duration.millis(70), node);
        // на сколько передвинется
        translateTransition.setFromX(0);
        translateTransition.setByX(10);
        // сколько раз данная анимация проиграет
        translateTransition.setCycleCount(5);
        // чтобы вернулось обратно
        translateTransition.setAutoReverse(true);
    }

    public void playAnimation() {
        translateTransition.playFromStart();
    }
}
