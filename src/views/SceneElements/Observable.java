package views.SceneElements;

import views.Observer;

import java.util.List;

public interface Observable {
    public void updateObservers();
    public void addObserver(views.Observer o);
}
