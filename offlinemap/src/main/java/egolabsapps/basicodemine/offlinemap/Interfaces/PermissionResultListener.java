package egolabsapps.basicodemine.offlinemap.Interfaces;

public class PermissionResultListener {

    private PermissionListener listener;

    private static PermissionResultListener taskDataObserver = null;

    public static PermissionResultListener getInstance() {
        if (taskDataObserver == null) {
            taskDataObserver = new PermissionResultListener();
        }
        return taskDataObserver;
    }

    public void setListener(PermissionListener listener) {
        this.listener = listener;
    }

    public interface PermissionListener {
        void onAccept();

        void onReject();
    }

    public void notifyUserAccept() {
        listener.onAccept();
    }

    public void notifyUserReject() {
        listener.onReject();
    }

}
