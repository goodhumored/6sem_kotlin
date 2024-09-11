import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.journaltodoapp.app.JournalFragment
import com.example.journaltodoapp.app.TodoListFragment

// Адаптер для ViewPager2, который будет управлять фрагментами
class ViewPagerAdapter(fragmentActivity: FragmentActivity) : FragmentStateAdapter(fragmentActivity) {

    // Определяем количество фрагментов (вкладок)
    override fun getItemCount(): Int {
        return 2 // У вас две вкладки: Журналы и Списки задач
    }

    // Возвращаем фрагмент в зависимости от позиции
    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> JournalFragment() // Вкладка для журналов
            1 -> TodoListFragment() // Вкладка для списка задач
            else -> Fragment() // На всякий случай возвращаем пустой фрагмент
        }
    }
}
