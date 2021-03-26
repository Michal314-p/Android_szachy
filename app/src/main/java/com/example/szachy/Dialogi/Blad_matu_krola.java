package com.example.szachy.Dialogi;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Blad_matu_krola extends AppCompatDialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle zapis)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Błąd!!!")
                .setMessage("Nie możesz ruszyć się na mat.")
                .setPositiveButton("OK", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i)
                    {

                    }
                });
        return builder.create();
    }
}
