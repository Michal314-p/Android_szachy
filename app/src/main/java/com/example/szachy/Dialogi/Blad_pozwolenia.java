package com.example.szachy.Dialogi;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatDialogFragment;

public class Blad_pozwolenia extends AppCompatDialogFragment
{
    @Override
    public Dialog onCreateDialog(Bundle zapis)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder
                .setTitle("Błąd")
                .setMessage("Nie udzielono odpowiedniej zgody.")
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
