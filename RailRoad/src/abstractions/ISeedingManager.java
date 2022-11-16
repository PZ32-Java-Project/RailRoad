package abstractions;

import models.*;

import java.util.List;

public interface ISeedingManager {
    public Client generateClient(List<Position> clients, List<Position> entrances);
    public List<Entrance> generateEntrances(int entrancesCount);
    public ReserveCashRegistry generateReserveCashRegistry();
    public List<CashRegistry> generateCashRegistries(int cashRegistriesCount);
    public Exit generateExit();
}