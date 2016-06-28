package com.example.takai.study_project;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hephalump on 12/02/2016.
 */
public class ModuleListAdapter extends ArrayAdapter<String> {
    private  Activity context;
    private int courseID;
    private  List<String> names = new ArrayList<>();
    private ModuleDataSource dataSource;
    List<ModuleModel> moduleModels =
            new ArrayList<ModuleModel>();



    public ModuleListAdapter(Activity context, List<String> names, int courseID) {
        super(context, R.layout.course_row_layout, names);
        this.names = names;
        this.context = context;
        this.courseID = courseID;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.content_module_list_row, parent, false);
        //rowView.setBackgroundColor(colors.get(position));
        TextView textViewName = (TextView) rowView.findViewById(R.id.moduleName);
        ImageView colorDisplay = (ImageView) rowView.findViewById(R.id.criticalColor);
        //colorDisplay.setBackgroundColor(colors.get(position));
        // ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
        textViewName.setText(names.get(position));
//        final FrameLayout menuButton = (FrameLayout) convertView.findViewById(R.id.overflowButtonBounds);
//        // add a menu button
//        menuButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(final View v) {
//                //Creating the instance of PopupMenu
//                PopupMenu popup = new PopupMenu(v.getContext(), menuButton);
//
//                //Inflating the Popup using xml file
//                popup.getMenuInflater()
//                        .inflate(R.menu.course_overflow, popup.getMenu());
//                //registering popup with OnMenuItemClickListener
//                popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
//                    public boolean onMenuItemClick(MenuItem item) {
//                        if (item.getItemId() == R.id.action_course_remove) {
//                            DeleteConfirmAlertDialog checkDelete = new DeleteConfirmAlertDialog();
//                            FragmentManager manager = context.getFragmentManager();
//                            Bundle deleteArgs = new Bundle();
//                            //deleteArgs.putInt("position", groupPosition);
//                            checkDelete.setArguments(deleteArgs);
//                            checkDelete.show(manager, "Confirm Delete");
//                        } else if (item.getItemId() == R.id.action_course_update) {
//                            CourseDialogueFragmentEdit editNameDialog = new CourseDialogueFragmentEdit();
//                            Bundle args = new Bundle();
//                            if (moduleModels.size() <= names.size()) {
//                                dataSource = new ModuleDataSource(context);
//                                try {
//                                    dataSource.open();
//                                } catch (SQLException e) {
//                                    e.printStackTrace();
//                                }
//                                // adding all the data items to a dataset so that the list can have values
//                                moduleModels = dataSource.getAllDataItems(courseID);
//                                dataSource.close();
//                            }
//                            args.putString("name", moduleModels.get(position).getName());
//                            args.putInt("position", position);
//                            editNameDialog.setArguments(args);
//                            FragmentManager manager = context.getFragmentManager();
//                            editNameDialog.show(manager, "fragment");
//
//                        }
//                        Toast.makeText(
//                                v.getContext(),
//                                "You Clicked : " + item.getTitle(),
//                                Toast.LENGTH_SHORT
//                        ).show();
//                        return true;
//                    }
//                });
//
//                popup.show(); //showing popup menu
//            }
//        }); //closing the setOnClic


        return rowView;
    }
    public void addObject(int size,String name){
        names.add(size, name);
    }
}
