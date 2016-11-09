package com.tienbi.marioproject.sound;

/**
 * Created by TienBi on 05/08/2016.
 */
public class PlayerManager {
    private static PlayerManager instance;

    public synchronized static PlayerManager getInstance() {
        if (instance == null) {
            instance = new PlayerManager();
        }
        return instance;
    }

    private PlayerWav sBG1;
    private PlayerWav sBG2;
    private PlayerWav sPlay;
    private PlayerWav sClick;
    private PlayerWav sBump;
    private PlayerWav sCoin;
    private PlayerWav sJump;
    private PlayerWav sPowerUp;
    private PlayerWav sDie;
    private PlayerWav sLevelDown;
    private PlayerWav sGameOver;
    private PlayerWav sLose;
    private PlayerWav sWin;
    private PlayerWav sCongChua;

    private PlayerManager() {
        sBG1 = new PlayerWav("gameselectscreen2");

        sBG2 = new PlayerWav("overworld");

        sPlay = new PlayerWav("enterGame");

        sClick = new PlayerWav("click");

        sBump = new PlayerWav("smb_fireball");

        sCoin = new PlayerWav("smw_coin");

        sJump = new PlayerWav("smw_jump");

        sPowerUp = new PlayerWav("smb_powerup");

        sDie = new PlayerWav("smb_mariodie");

        sLevelDown = new PlayerWav("smb_bowserfalls");

        sGameOver = new PlayerWav("smb_gameover");

        sLose = new PlayerWav("lose");

        sWin = new PlayerWav("win");

        sCongChua = new PlayerWav("smb_stage_clear");
    }

    public PlayerWav getsBG1() {
        return sBG1;
    }

    public PlayerWav getsLose() {
        return sLose;
    }

    public PlayerWav getsWin() {
        return sWin;
    }

    public PlayerWav getsClick() {
        return sClick;
    }

    public PlayerWav getsCoin() {
        return sCoin;
    }

    public PlayerWav getsJump() {
        return sJump;
    }

    public PlayerWav getsPowerUp() {
        return sPowerUp;
    }

    public PlayerWav getsBG2() {
        return sBG2;
    }

    public PlayerWav getsPlay() {
        return sPlay;
    }

    public PlayerWav getsBump() {
        return sBump;
    }

    public PlayerWav getsDie() {
        return sDie;
    }

    public PlayerWav getsLevelDown() {
        return sLevelDown;
    }

    public PlayerWav getsGameOver() {
        return sGameOver;
    }

    public PlayerWav getsCongChua() {
        return sCongChua;
    }
}
