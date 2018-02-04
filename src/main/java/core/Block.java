package core;

import util.StringUtil;

import java.util.Date;

public class Block{
    public String hash;
    public String previousHash;
    private String data;
    private long timestamp;

    private int nonce;

    public Block(String data, String previousHash){
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = new Date().getTime();
        this.hash = calculateHash();
    }

    public String calculateHash(){
        String calculatehash = StringUtil.applySha256(
                previousHash + Long.toString(timestamp) + Integer.toString(nonce) + data
        );
    //    System.out.println("caculatehash " + calculatehash);

        return calculatehash;
    }

    public void mineBlock(int difficulty){
        String target = new String(new char[difficulty]).replace('\0', '0');

        while(!hash.substring(0, difficulty).equals(target)){
            nonce++;
            hash = calculateHash();

            System.out.printf("\nGEN Hash #%d : %s", nonce, hash);
        }
        System.out.printf("\n채굴 성공!!: " + hash);
    }



}