    package com.example.userlist_1147;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    UserAdapter userAdapter;
    //ArrayList<String> usersName = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*for (int i = 0; i < 100; i++) {
            usersName.add("User_"+i);
        }*/

        recyclerView = findViewById(R.id.recyclerView);//связываем с ксмл
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));//располагем элементы на экране в одномерном списке(обращаемся к нашему классу через контекст)
        Users users = new Users();// создаем обьект класса юсерс
        userAdapter = new UserAdapter(users.getUserList());// создаем обьект для класса юзер адаптер и вкладываем список юзеров в адаптер
        recyclerView.setAdapter(userAdapter);// связваем ресйклер с адаптером
    }

    // Класс элемента списка
    private class UserHolder extends RecyclerView.ViewHolder{// создаем класс (расширяем ресйклер виев)для отображения элдемнов списка
        TextView itemTextView;//этот класс создает
        String userName;
        public UserHolder(LayoutInflater inflater, ViewGroup viewGroup) {// создаем конструктор для класса принимаем из мтода онкрейт раздутый макет и группу компонентов на экране

            super(inflater.inflate(R.layout.single_item, viewGroup, false));// раздуваем наш макет для элементов добавляем группу элементов в конструкторе родительского класса
            itemTextView = itemView.findViewById(R.id.itemTextView);// наследуемся от класса ресайклер виев и записываем в переменную
        }
        public void bind(String userName){//публичный метод для того что бы мочь вызывать из другого класса
            this.userName = userName;// принимает строк юзер нейм
            itemTextView.setText(userName);//в текствиев добавляем юзернаме
        }
    }

    private class UserAdapter extends RecyclerView.Adapter<UserHolder>{//создаем класс для рисовки элемнтов списка
        ArrayList<User> usersName;// записываем сюда массив
        public UserAdapter(ArrayList<User> usersName) {// конструктор    (сюда приходит массив и через конструктор копируется в юсернаме)
            this.usersName = usersName;// принмаем юзер нейм строку
        }// принимаем аррайлист на вход из юсерс

        @Override
        public UserHolder onCreateViewHolder(ViewGroup parent, int viewType) {// возвращает один элемент списка
            LayoutInflater inflater = LayoutInflater.from(MainActivity.this);// раздувает макет(наполняет единицу макета)
            return new UserHolder(inflater, parent);// возвращаем юсер холдер в юсерхолдер андроид запихиват вьюгрупп
        }

        @Override
        public void onBindViewHolder(UserHolder userHolder, int position) {//привязываем список к позициям
            User user = usersName.get(position);// из коллекции достаю кого то по позиции и записываем в юзера
            userHolder.bind(user.getUserName()+" "+user.getUserLastname());//связываем имя пользователя в с вюхолдером(добавляем в макет)
        }

        @Override
        public int getItemCount() {
            return usersName.size();//размещаем размер списка аррай листа
        }// метод говорит сколько элементов надо разместить
    }
}