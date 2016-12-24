package view;

/*==================================================================
 * Author: Erin Avllazagaj AKA "Albocoder"
 * Website: http://erin.avllazagaj.ug.bilkent.edu.tr
 * Date: Dec/07/2016
 * Version: 2.0.0
 *==================================================================
 * Referrer: https://github.com/Albocoder/CS319-Group22
 *==================================================================
 * Changelog:
 * Changed names of methods due to overriding issues.
 *==================================================================
 * Description:
 * This interface is to be implemented by all the views and enables
 * use of polymorphism in all the view classes regardless of their
 * content.
 * */

/**
 *
 * @author kaxell
 */

public interface Viewable {

    /**
     *
     */
    ViewManager referrer = null;

    /**
     *
     */
    public void terminateView();

    /**
     *
     */
    public void hideView();

    /**
     *
     */
    public void updateView();
		
    /**
     *
     */
    public void showView();		
}