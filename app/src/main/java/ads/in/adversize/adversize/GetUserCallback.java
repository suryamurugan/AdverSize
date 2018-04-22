package ads.in.adversize.adversize;

/**
 * Created by suryamurugan on 8/4/18.
 */
interface GetUserCallback {

    /**
     * Invoked when background task is completed
     */

    public abstract void done(User returnedUser);
}

