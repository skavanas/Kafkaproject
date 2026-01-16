import { useState, useEffect } from "react";
import { getNotificationsByParent } from "../services/notificationService";

const NotificationPanel = ({ parentId = "parent-123" }) => {
  const [notifications, setNotifications] = useState([]);
  const [loading, setLoading] = useState(true);

  const loadNotifications = async () => {
    const data = await getNotificationsByParent(parentId);
    setNotifications(data);
    setLoading(false);
  };

  useEffect(() => {
    loadNotifications();

    // Rafraîchit toutes les 3 secondes
    const interval = setInterval(loadNotifications, 3000);
    return () => clearInterval(interval);
  }, [parentId]);

  const markAsRead = (index) => {
    const updated = [...notifications];
    updated.splice(index, 1);
    setNotifications(updated);
  };

  return (
    <div style={styles.container}>
      <div style={styles.header}>
        <h2 style={styles.title}>🔔 Notifications</h2>
        {notifications.length > 0 && (
          <span style={styles.badge}>{notifications.length}</span>
        )}
      </div>

      {loading ? (
        <div style={styles.loading}>
          <div style={styles.spinner}></div>
        </div>
      ) : notifications.length === 0 ? (
        <div style={styles.empty}>
          <p style={styles.emptyIcon}>📭</p>
          <p style={styles.emptyText}>Aucune notification</p>
        </div>
      ) : (
        <div style={styles.notificationList}>
          {notifications.map((notif, index) => (
            <div key={index} style={styles.notificationCard}>
              <div style={styles.notifHeader}>
                <span style={styles.busIcon}>🚌</span>
                <span style={styles.notifTime}>
                  {new Date().toLocaleTimeString("fr-FR")}
                </span>
              </div>
              
              <p style={styles.message}>{notif.message}</p>
              
              <button
                onClick={() => markAsRead(index)}
                style={styles.dismissBtn}
              >
                ✓ Marquer comme lu
              </button>
            </div>
          ))}
        </div>
      )}

      <div style={styles.parentInfo}>
        <p style={styles.parentText}>
          👤 Parent ID: <strong>{parentId}</strong>
        </p>
      </div>
    </div>
  );
};

const styles = {
  container: {
    background: "white",
    borderRadius: "12px",
    boxShadow: "0 4px 15px rgba(0,0,0,0.1)",
    padding: "20px",
    maxHeight: "500px",
    display: "flex",
    flexDirection: "column",
  },
  header: {
    display: "flex",
    alignItems: "center",
    justifyContent: "space-between",
    marginBottom: "20px",
    paddingBottom: "15px",
    borderBottom: "2px solid #f0f0f0",
  },
  title: {
    margin: 0,
    fontSize: "22px",
    color: "#2c3e50",
  },
  badge: {
    background: "#e74c3c",
    color: "white",
    borderRadius: "50%",
    width: "30px",
    height: "30px",
    display: "flex",
    alignItems: "center",
    justifyContent: "center",
    fontSize: "14px",
    fontWeight: "bold",
  },
  loading: {
    display: "flex",
    justifyContent: "center",
    padding: "40px",
  },
  spinner: {
    width: "40px",
    height: "40px",
    border: "4px solid #f3f3f3",
    borderTop: "4px solid #667eea",
    borderRadius: "50%",
    animation: "spin 1s linear infinite",
  },
  empty: {
    textAlign: "center",
    padding: "50px 20px",
    color: "#95a5a6",
  },
  emptyIcon: {
    fontSize: "48px",
    margin: 0,
  },
  emptyText: {
    fontSize: "16px",
    marginTop: "10px",
  },
  notificationList: {
    flex: 1,
    overflowY: "auto",
    display: "flex",
    flexDirection: "column",
    gap: "12px",
  },
  notificationCard: {
    background: "linear-gradient(135deg, #667eea 0%, #764ba2 100%)",
    color: "white",
    padding: "16px",
    borderRadius: "10px",
    boxShadow: "0 4px 10px rgba(102, 126, 234, 0.3)",
    animation: "slideIn 0.3s ease",
  },
  notifHeader: {
    display: "flex",
    justifyContent: "space-between",
    alignItems: "center",
    marginBottom: "10px",
  },
  busIcon: {
    fontSize: "24px",
  },
  notifTime: {
    fontSize: "12px",
    opacity: 0.8,
  },
  message: {
    fontSize: "15px",
    lineHeight: "1.5",
    margin: "10px 0",
  },
  dismissBtn: {
    background: "rgba(255, 255, 255, 0.2)",
    color: "white",
    border: "1px solid rgba(255, 255, 255, 0.3)",
    padding: "8px 16px",
    borderRadius: "6px",
    cursor: "pointer",
    fontSize: "13px",
    fontWeight: "500",
    transition: "all 0.2s",
    marginTop: "8px",
  },
  parentInfo: {
    marginTop: "15px",
    paddingTop: "15px",
    borderTop: "2px solid #f0f0f0",
  },
  parentText: {
    fontSize: "13px",
    color: "#7f8c8d",
    margin: 0,
  },
};

export default NotificationPanel;