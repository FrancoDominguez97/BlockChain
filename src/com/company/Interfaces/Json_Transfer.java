package com.company.Interfaces;

import com.company.Transferencias.Transaction;

import java.util.List;

public interface Json_Transfer {
    public static List<Transaction> JsonToListTransfer(String file){ return null; }
    public static Transaction searchByIdSender(String file, String sender){ return null; }
    public static Transaction searchByIdReceiver(String file, String receiver){ return null; }
    public static void showTransactionSender(String file,String sender){ }
    public static void showTransactionReceiver(String file,String receiver){ }
    public static List<Transaction> pendingTransfer(String file){ return null; }
}
