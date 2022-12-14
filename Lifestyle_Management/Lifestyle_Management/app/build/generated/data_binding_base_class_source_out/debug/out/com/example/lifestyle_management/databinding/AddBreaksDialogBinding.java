// Generated by view binder compiler. Do not edit!
package com.example.lifestyle_management.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.Guideline;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.example.lifestyle_management.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class AddBreaksDialogBinding implements ViewBinding {
  @NonNull
  private final ConstraintLayout rootView;

  @NonNull
  public final EditText breakTitle;

  @NonNull
  public final Button datePicker;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final Guideline guideline2;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   */
  @Nullable
  public final Guideline guideline3;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   */
  @Nullable
  public final Guideline guideline4;

  /**
   * This binding is not available in all configurations.
   * <p>
   * Present:
   * <ul>
   *   <li>layout/</li>
   * </ul>
   *
   * Absent:
   * <ul>
   *   <li>layout-land/</li>
   * </ul>
   */
  @Nullable
  public final Guideline guideline5;

  @NonNull
  public final Button timePicker;

  private AddBreaksDialogBinding(@NonNull ConstraintLayout rootView, @NonNull EditText breakTitle,
      @NonNull Button datePicker, @Nullable Guideline guideline2, @Nullable Guideline guideline3,
      @Nullable Guideline guideline4, @Nullable Guideline guideline5, @NonNull Button timePicker) {
    this.rootView = rootView;
    this.breakTitle = breakTitle;
    this.datePicker = datePicker;
    this.guideline2 = guideline2;
    this.guideline3 = guideline3;
    this.guideline4 = guideline4;
    this.guideline5 = guideline5;
    this.timePicker = timePicker;
  }

  @Override
  @NonNull
  public ConstraintLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static AddBreaksDialogBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static AddBreaksDialogBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.add_breaks_dialog, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static AddBreaksDialogBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.break_title;
      EditText breakTitle = ViewBindings.findChildViewById(rootView, id);
      if (breakTitle == null) {
        break missingId;
      }

      id = R.id.date_picker;
      Button datePicker = ViewBindings.findChildViewById(rootView, id);
      if (datePicker == null) {
        break missingId;
      }

      id = R.id.guideline2;
      Guideline guideline2 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.guideline3;
      Guideline guideline3 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.guideline4;
      Guideline guideline4 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.guideline5;
      Guideline guideline5 = ViewBindings.findChildViewById(rootView, id);

      id = R.id.time_picker;
      Button timePicker = ViewBindings.findChildViewById(rootView, id);
      if (timePicker == null) {
        break missingId;
      }

      return new AddBreaksDialogBinding((ConstraintLayout) rootView, breakTitle, datePicker,
          guideline2, guideline3, guideline4, guideline5, timePicker);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
