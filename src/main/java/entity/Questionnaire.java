/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author Tarllark
 */
@Entity
@Table(name="questionnaire")
public class Questionnaire implements Serializable
{
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_smoke", columnDefinition="tinyint(1)")
    private boolean questionnaireSmoke;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_pet", columnDefinition="tinyint(1)")
    private boolean questionnairePet;
    @ManyToOne
    @JoinColumn(name="questionnaire_area")
    private CityInfo questionnaireArea;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_sport", columnDefinition="tinyint(1)")
    private boolean questionnaireSport;
    @NotNull
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="questionnaire_budget")
    private Budget questionnaireBudget;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_music", columnDefinition="tinyint(1)")
    private boolean questionnaireMusic;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="questionnaire_reason")
    private Reason questionnaireReason;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_party", columnDefinition="tinyint(1)")
    private boolean questionnaireParty;
    @Basic(optional = false)
    @NotNull
    @JoinColumn(name = "questionnaire_single", columnDefinition="tinyint(1)")
    private boolean questionnaireSingle;
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="questionnaire_clean")
    private CleanLevel questionnaireClean;
    

    public Questionnaire() {
    }

    public Questionnaire(int id) {
        this.id = id;
    }

    public Integer getId()
    {
        return id;
    }

    public boolean isQuestionnaireSmoke()
    {
        return questionnaireSmoke;
    }

    public void setQuestionnaireSmoke(boolean questionnaireSmoke)
    {
        this.questionnaireSmoke = questionnaireSmoke;
    }

    public boolean isQuestionnairePet()
    {
        return questionnairePet;
    }

    public void setQuestionnairePet(boolean questionnairePet)
    {
        this.questionnairePet = questionnairePet;
    }
    public boolean isQuestionnaireSport()
    {
        return questionnaireSport;
    }

    public void setQuestionnaireSport(boolean questionnaireSport)
    {
        this.questionnaireSport = questionnaireSport;
    }

    public boolean isQuestionnaireMusic()
    {
        return questionnaireMusic;
    }

    public void setQuestionnaireMusic(boolean questionnaireMusic)
    {
        this.questionnaireMusic = questionnaireMusic;
    }
    public boolean isQuestionnaireParty()
    {
        return questionnaireParty;
    }

    public void setQuestionnaireParty(boolean questionnaireParty)
    {
        this.questionnaireParty = questionnaireParty;
    }

    public boolean isQuestionnaireSingle()
    {
        return questionnaireSingle;
    }

    public void setQuestionnaireSingle(boolean questionnaireSingle)
    {
        this.questionnaireSingle = questionnaireSingle;
    }

    public CityInfo getQuestionnaireArea()
    {
        return questionnaireArea;
    }

    public void setQuestionnaireArea(CityInfo questionnaireArea)
    {
        this.questionnaireArea = questionnaireArea;
    }

    public Budget getQuestionnaireBudget()
    {
        return questionnaireBudget;
    }

    public void setQuestionnaireBudget(Budget questionnaireBudget)
    {
        this.questionnaireBudget = questionnaireBudget;
    }

    public Reason getQuestionnaireReason()
    {
        return questionnaireReason;
    }

    public void setQuestionnaireReason(Reason questionnaireReason)
    {
        this.questionnaireReason = questionnaireReason;
    }

    public CleanLevel getQuestionnaireClean()
    {
        return questionnaireClean;
    }

    public void setQuestionnaireClean(CleanLevel questionnaireClean)
    {
        this.questionnaireClean = questionnaireClean;
    }

 
    
}
