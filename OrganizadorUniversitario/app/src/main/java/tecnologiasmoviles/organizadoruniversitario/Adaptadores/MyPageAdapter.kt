package tecnologiasmoviles.organizadoruniversitario.Adaptadores

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentCurso
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentHorario
import tecnologiasmoviles.organizadoruniversitario.my_fragment.FragmentNota

class MyPageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 ->{
                // creamos fragment
                FragmentCurso()
            }
            1->{
                FragmentHorario()
            }
            else->{
                return FragmentNota()
            }
        }
    }

    override fun getCount(): Int {
        return 3
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0->"Curso"
            1->"Horario"
            else-> {return "Notas"}
        }
    }
}